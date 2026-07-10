const MemberAPI = {
    getAll(part) {
        const url = part ? `/members?part=${encodeURIComponent(part)}` : '/members';
        return httpFetch(url);
    },
    getById(id) {
        return httpFetch(`/members/${id}`);
    },
    createLion(data) {
        return httpFetch('/members/lions', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),
        });
    },
    createStaff(data) {
        return httpFetch('/members/staffs', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),
        });
    },
    updateLion(id, data) {
        return httpFetch(`/members/lions/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),
        });
    },
    updateStaff(id, data) {
        return httpFetch(`/members/staffs/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),
        });
    },
    delete(id) {
        return httpFetch(`/members/${id}`, { method: 'DELETE' });
    },
};

const MemberUI = {
    container: null,
    editingId: null,
    editingRole: null,

    initMemberUI() {
        this.container = document.getElementById('member-tab');
        this.container.innerHTML = `
            <div class="panel">
                <h3>멤버 등록</h3>
                <div class="grid">
                    <label>역할
                        <select id="member-role">
                            <option value="LION">LION</option>
                            <option value="STAFF">STAFF</option>
                        </select>
                    </label>
                    <label>이름<input id="member-name" type="text"></label>
                    <label>전공<input id="member-major" type="text"></label>
                    <label>기수<input id="member-generation" type="number" value="13"></label>
                    <label>파트<input id="member-part" type="text" placeholder="백엔드"></label>
                    <label id="member-student-id-label">학번<input id="member-student-id" type="text"></label>
                    <label id="member-position-label" style="display:none;">직책<input id="member-position" type="text"></label>
                </div>
                <div class="actions">
                    <button id="member-create-btn" type="button">등록</button>
                </div>
            </div>

            <div class="panel">
                <h3>멤버 조회 / 필터</h3>
                <div class="grid">
                    <label>파트 필터<input id="member-filter-part" type="text" placeholder="백엔드"></label>
                    <label>단건 ID<input id="member-find-id" type="number"></label>
                </div>
                <div class="actions">
                    <button id="member-load-all-btn" type="button">전체 조회</button>
                    <button id="member-filter-btn" type="button" class="secondary">파트별 조회</button>
                    <button id="member-find-btn" type="button" class="secondary">단건 조회</button>
                </div>
            </div>

            <div class="panel">
                <h3>멤버 수정</h3>
                <div class="grid">
                    <label>ID<input id="member-edit-id" type="number" readonly></label>
                    <label>이름<input id="member-edit-name" type="text" readonly></label>
                    <label>전공<input id="member-edit-major" type="text"></label>
                    <label>기수<input id="member-edit-generation" type="number"></label>
                    <label>파트<input id="member-edit-part" type="text"></label>
                    <label id="member-edit-student-id-label">학번<input id="member-edit-student-id" type="text"></label>
                    <label id="member-edit-position-label" style="display:none;">직책<input id="member-edit-position" type="text"></label>
                </div>
                <div class="actions">
                    <button id="member-save-btn" type="button">저장</button>
                </div>
            </div>

            <div class="panel">
                <h3>멤버 목록</h3>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>이름</th>
                            <th>전공</th>
                            <th>기수</th>
                            <th>파트</th>
                            <th>역할</th>
                            <th>학번/직책</th>
                            <th>작업</th>
                        </tr>
                    </thead>
                    <tbody id="member-table-body"></tbody>
                </table>
            </div>
        `;

        document.getElementById('member-role').addEventListener('change', this.toggleRoleFields.bind(this));
        document.getElementById('member-create-btn').addEventListener('click', () => this.createMember());
        document.getElementById('member-load-all-btn').addEventListener('click', () => this.loadMembers());
        document.getElementById('member-filter-btn').addEventListener('click', () => this.filterMembers());
        document.getElementById('member-find-btn').addEventListener('click', () => this.findMember());
        document.getElementById('member-save-btn').addEventListener('click', () => this.saveMember());
        this.toggleRoleFields();
    },

    toggleRoleFields() {
        const role = document.getElementById('member-role').value;
        const showStudent = role === 'LION';
        document.getElementById('member-student-id-label').style.display = showStudent ? 'flex' : 'none';
        document.getElementById('member-position-label').style.display = showStudent ? 'none' : 'flex';
    },

    async loadMembers(part) {
        const members = await MemberAPI.getAll(part);
        this.renderMembers(members);
        if (typeof AssignmentUI !== 'undefined') {
            AssignmentUI.refreshMemberSelect(members);
        }
    },

    async filterMembers() {
        const part = document.getElementById('member-filter-part').value.trim();
        await this.loadMembers(part || undefined);
    },

    async findMember() {
        const id = document.getElementById('member-find-id').value;
        if (!id) return;
        const member = await MemberAPI.getById(id);
        this.renderMembers([member]);
    },

    async createMember() {
        const role = document.getElementById('member-role').value;
        const payload = {
            name: document.getElementById('member-name').value.trim(),
            major: document.getElementById('member-major').value.trim(),
            generation: Number(document.getElementById('member-generation').value),
            part: document.getElementById('member-part').value.trim(),
        };

        if (role === 'LION') {
            payload.studentId = document.getElementById('member-student-id').value.trim();
            await MemberAPI.createLion(payload);
        } else {
            payload.position = document.getElementById('member-position').value.trim();
            await MemberAPI.createStaff(payload);
        }

        showToast('멤버가 등록되었습니다.', 'success');
        await this.loadMembers();
    },

    startEdit(member) {
        this.editingId = member.id;
        this.editingRole = member.roleName === '운영진' ? 'STAFF' : 'LION';
        document.getElementById('member-edit-id').value = member.id;
        document.getElementById('member-edit-name').value = member.name;
        document.getElementById('member-edit-major').value = member.major;
        document.getElementById('member-edit-generation').value = member.generation;
        document.getElementById('member-edit-part').value = member.part;

        const isLion = this.editingRole === 'LION';
        document.getElementById('member-edit-student-id-label').style.display = isLion ? 'flex' : 'none';
        document.getElementById('member-edit-position-label').style.display = isLion ? 'none' : 'flex';
        document.getElementById('member-edit-student-id').value = member.studentId || '';
        document.getElementById('member-edit-position').value = member.position || '';
    },

    async saveMember() {
        if (!this.editingId) return;

        const payload = {
            major: document.getElementById('member-edit-major').value.trim(),
            generation: Number(document.getElementById('member-edit-generation').value),
            part: document.getElementById('member-edit-part').value.trim(),
        };

        if (this.editingRole === 'LION') {
            payload.studentId = document.getElementById('member-edit-student-id').value.trim();
            await MemberAPI.updateLion(this.editingId, payload);
        } else {
            payload.position = document.getElementById('member-edit-position').value.trim();
            await MemberAPI.updateStaff(this.editingId, payload);
        }

        showToast('멤버 정보가 수정되었습니다.', 'success');
        await this.loadMembers();
    },

    async removeMember(id) {
        await MemberAPI.delete(id);
        showToast('멤버가 삭제되었습니다.', 'success');
        await this.loadMembers();
    },

    renderMembers(members) {
        const tbody = document.getElementById('member-table-body');
        tbody.innerHTML = members.map((member) => `
            <tr>
                <td>${member.id}</td>
                <td>${member.name}</td>
                <td>${member.major}</td>
                <td>${member.generation}</td>
                <td>${member.part}</td>
                <td>${member.roleName}</td>
                <td>${member.studentId || member.position || '-'}</td>
                <td>
                    <button type="button" class="secondary" data-edit-id="${member.id}">수정</button>
                    <button type="button" class="danger" data-delete-id="${member.id}">삭제</button>
                </td>
            </tr>
        `).join('');

        tbody.querySelectorAll('[data-edit-id]').forEach((button) => {
            button.addEventListener('click', () => {
                const member = members.find((item) => item.id === Number(button.dataset.editId));
                if (member) this.startEdit(member);
            });
        });

        tbody.querySelectorAll('[data-delete-id]').forEach((button) => {
            button.addEventListener('click', () => this.removeMember(Number(button.dataset.deleteId)));
        });
    },
};

function initMemberUI() {
    MemberUI.initMemberUI();
}
